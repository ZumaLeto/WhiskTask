package model;

public class Whisk {

    private String name;
    private Boolean primary;

    public Whisk(String name, Boolean primary) {
        this.name = name;
        this.primary = primary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }
}
