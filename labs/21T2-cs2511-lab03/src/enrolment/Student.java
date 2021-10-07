package enrolment;
import java.util.ArrayList;

public class Student {

    private String zid;
    private ArrayList<Enrolment> enrolments;

	public Student(String zid) {
        this.zid = zid;
        enrolments = new ArrayList<>();
    }

	public String getZID() {
		return zid;
	}

    public ArrayList<Enrolment> getEnrolments() { return enrolments;  }

    public boolean getEnrolled(CourseOffering courseOffering) {
	    if (!courseOffering.isMeetingPrerequisite(this))
            return false;
        Enrolment enrolment = new Enrolment(courseOffering, this);
        enrolments.add(enrolment);
        return true;
    }

    public void receiveGrade(CourseOffering courseOffering, Grade grade) {
	    for (Enrolment enrolment: enrolments) {
	        if (enrolment.getOffering() == courseOffering) {
	            enrolment.setGrade(grade);
            }
        }
    }

    public String getGrade(CourseOffering courseOffering) {
        for (Enrolment enrolment: enrolments) {
            if (enrolment.getOffering() == courseOffering) {
                return enrolment.getGrade();
            }
        }
        return null;
    }
}
