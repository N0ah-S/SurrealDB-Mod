package org.cinemacraftstudios.util;

public class BinaryStarterBuilder {

    StringBuilder builder;

    public BinaryStarterBuilder() {
        builder = new StringBuilder();
    }

    public BinaryStarterBuilder(String pathToBinary) {
        builder = new StringBuilder(pathToBinary);
    }

    public BinaryStarterBuilder arg(String arg, String value) {
        builder.append(" -");
        builder.append(arg);
        builder.append(" ");
        //builder.append("\"");
        builder.append(value);
        //builder.append("\"");
        return this;
    }

    public BinaryStarterBuilder arg(String arg) {
        builder.append(" ");
        builder.append(arg);
        return this;
    }

    public BinaryStarterBuilder flag(String flag) {
        builder.append(" -");
        builder.append(flag);
        return this;
    }

    public BinaryStarterBuilder append(String s) {
        builder.append(s);
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
