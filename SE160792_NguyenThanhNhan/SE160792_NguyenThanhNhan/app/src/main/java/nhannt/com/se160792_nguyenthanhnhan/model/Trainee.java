package nhannt.com.se160792_nguyenthanhnhan.model;

public class Trainee {

    private long id;
    private String name;
    private String date;
    private String salary;
    private String gender;

    public Trainee(String name, String date, String salary, String gender) {
        this.name = name;
        this.date = date;
        this.salary = salary;
        this.gender = gender;
    }

    public Trainee(long id, String name, String date, String salary, String gender) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.salary = salary;
        this.gender = gender;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
