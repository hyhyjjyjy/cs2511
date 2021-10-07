package staff;

/**
 * A staff member
 * @author Bunny-Dong
 *
 */
public class StaffMember {
    private final String name;
    private int salary;
    private String hireDate;
    private String endDate;


    /**
     * 4 argus constructor
     * @param name name
     * @param salary salary
     * @param hireDate hireDate
     * @param endDate endDate
     */
    public StaffMember(String name, int salary, String hireDate, String endDate) {
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
        this.endDate = endDate;
    }

    /**
     * The method return the name
     * @return return name
     */
    public String getName() {
        return name;
    }

    /**
     * the method return salary
     * @return salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * the method return hireDate
     * @return hireDate
     */
    public String getHireDate() {
        return hireDate;
    }

    /**
     * the method return endDate
     * @return endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * the method set the salary
     * @param salary salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * the method set the hireDate
     * @param hireDate hireDate
     */
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * the method set the endDate
     * @param endDate endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) return false;
        StaffMember newObjet = (StaffMember) object;
        if (newObjet.getName().compareTo(name) != 0
                || newObjet.getHireDate().compareTo(hireDate) != 0
                || newObjet.getEndDate().compareTo(endDate) != 0)
            return false;
        return newObjet.getSalary() == salary;
    }

    @Override
    public String toString() {
        return "StaffMember " + name +
                " has salary = " + salary +
                " with hireDate = " + hireDate +
                ", endDate= " + endDate + '\n';
    }
}
