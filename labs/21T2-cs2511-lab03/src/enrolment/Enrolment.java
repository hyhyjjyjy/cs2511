package enrolment;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade = null;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.student = student;
    }

    public CourseOffering getOffering() { return offering; }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getGrade() { 
        if (grade == null)
            return null;
        
        return grade.getGrade(); 
    }

    public void setGrade(Grade grade) { this.grade = grade; }


}
