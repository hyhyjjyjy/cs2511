package staff;

public class Lecturer extends StaffMember{
    private String school;
    private Character status;

    public Lecturer(String name, int salary, String hireDate, String endDate, String school, Character status) {
        super(name, salary, hireDate, endDate);
        this.school = school;
        this.status = status;
    }

    public String getSchool() {
        return school;
    }

    public Character getStatus() {
        return status;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lecturer's name is " + this.getName() + " in " +
                "school " + school + ' ' +
                "with status " + status + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lecturer newObjct = (Lecturer) o;
        return this.school.compareTo(newObjct.getSchool()) == 0 && getStatus() == newObjct.getStatus();

    }
}
