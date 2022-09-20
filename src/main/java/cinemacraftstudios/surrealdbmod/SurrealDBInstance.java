package cinemacraftstudios.surrealdbmod;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.*;

public class SurrealDBInstance {

    public SurrealDBInstance(File jarFile) {
        File parent = jarFile.getParentFile();

        //region Assuring the required binary is available

        // Corrects path in dev-environment
        // TODO tidy the check up (might cause error when the user has interesting .minecraft paths)
        if(parent.getAbsolutePath().contains("build\\classes\\java")) {
            try {
                parent = new File(parent, "../../../run/mods").getCanonicalFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!parent.getName().equalsIgnoreCase("surrealdb")) {
            parent = new File(parent, "surrealdb");
        }

        // no version number in file name case we are offline (no updates)
        File exe = new File(parent, "surrealdb-current.exe");

        if(!exe.exists()) {

            HttpResponse<String> version = Unirest.get("https://version.surrealdb.com/").asString();
            if(!version.isSuccess()) {
                SurrealDBMod.logger.error("Failed to retrieve version. You need to be connected to the internet, " +
                                            "or provide the surrealdb binary yourself.");
                return;
            }

            String vParam = version.getBody().replace("\n", "");
            String downloadURL = String.format("https://download.surrealdb.com/%s/surreal-%s.windows-amd64.exe",
                    vParam, vParam);

            SurrealDBMod.logger.info(String.format("Starting download of SurrealDB from: %s", downloadURL));
            SurrealDBMod.logger.info(String.format("To: %s", exe.getAbsolutePath()));

            if(!exe.getParentFile().mkdirs()) {
                SurrealDBMod.logger.error(String.format("Couldn't create folder(s): %s.", exe.getParent()));
            }

            //Download the binary
            if(!Unirest.get(downloadURL)
                    .asFile(exe.getPath())
                    .isSuccess()) {
                SurrealDBMod.logger.error("Failed to retrieve binary. Contact the author to adjust to the change of the download " +
                                            "system, or provide the surrealdb binary yourself.");
                return;
            } else {
                SurrealDBMod.logger.info("Successfully downloaded the binary");
            }


        }
        //endregion

        //region Starting surrealdb
        try {
            ProcessBuilder   ps = new ProcessBuilder(exe.getAbsolutePath(),"start");

            ps.redirectErrorStream(true);

            Process pr = ps.start();

            // Making sure the service is stopped on termination
            Runtime.getRuntime().addShutdownHook(new Thread(() -> killSurrealServer(pr)));

            // Redirecting the output to the standard logger
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            SurrealDBMod.logger.error("Couldn't start surrealdb");
            SurrealDBMod.logger.error(e.getMessage());
            e.printStackTrace();
        }
        //endregion
    }

    private void killSurrealServer(Process pr) {
        SurrealDBMod.logger.info("Stopping SurrealDB Server");

        try {
            SurrealDBMod.logger.info("Stopped SurrealDB Server with exit code " +
                            pr.destroyForcibly().waitFor());
        } catch (InterruptedException e) {
            SurrealDBMod.logger.error("Unable to stop SurrealDB Server");
            SurrealDBMod.logger.error("You might need to kill the process yourself.");
            SurrealDBMod.logger.error("Use \"Get-Process -Id (Get-NetTCPConnection -LocalPort 8000).OwningProcess\" to find out the id");
            SurrealDBMod.logger.error("Then \"kill <id>\" where <id> is replaced by what you just found out.");
            e.printStackTrace();
        }
    }
}
