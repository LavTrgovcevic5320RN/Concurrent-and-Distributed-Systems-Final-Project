package app;

import java.io.Serializable;
import java.util.Objects;

public class MyFile implements Serializable {
    private static final long serialVersionUID = 53041700423511555L;
    private String path;
    private String visibility;
    private ServentInfo owner;

    public MyFile(String path, String visibility, ServentInfo owner) {
        this.path = path;
        this.visibility = visibility;
        this.owner = owner;
    }

    public MyFile(String path, ServentInfo owner) {
        this.path = path;
        this.owner = owner;
    }

    public String getPath() {
        return path;
    }

    public String getVisibility() {
        return visibility;
    }

    public ServentInfo getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyFile myFile)) return false;
        return Objects.equals(path, myFile.path) && Objects.equals(owner, myFile.owner);
    }
}
