package com.fodala.pojo;

public class FileView {
    private String name;
    private boolean isDirectory;
    private String path;
    private String pathSeparator;

    public FileView(String name, boolean isDirectory, String path, String pathSeparator) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.path = path;
        this.pathSeparator = pathSeparator;
    }

    public FileView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathSeparator() {
        return pathSeparator;
    }

    public void setPathSeparator(String pathSeparator) {
        this.pathSeparator = pathSeparator;
    }
}
