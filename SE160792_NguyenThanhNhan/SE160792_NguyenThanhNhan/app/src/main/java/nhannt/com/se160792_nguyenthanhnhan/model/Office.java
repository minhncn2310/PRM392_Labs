package nhannt.com.se160792_nguyenthanhnhan.model;

public class Office {

    private long id;
    private String name;

    public Office(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Office(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
