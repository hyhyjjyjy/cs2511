package enrolment;
import java.util.ArrayList;
import java.util.List;

public class CourseOffering {

    private Course course;
    private String term;
    private List<Session> sessions;
    private List<Enrolment> enrolments;

    public CourseOffering(Course course, String term) {
        this.course = course;
        this.term = term;
        this.sessions = new ArrayList<>();
        this.enrolments = new ArrayList<>();
    }

    public void addSession(Session session) {
        if (session == null)
            return;
        sessions.add(session);
    }

    public Course getCourse() {
        return course;
    }

    public String getTerm() {
        return term;
    }

    public List<Enrolment> getEnrolments() { return enrolments; }

    public List<Session> getSessions() { return sessions; }

    public void addEnrolment(Enrolment enrolment) {enrolments.add(enrolment); }

    public boolean isMeetingPrerequisite(Student student) {
        List<Course> preRequisites = course.getPreRequisites();
        for (Course preRequisite: preRequisites) {
            List<Enrolment> enrolments = student.getEnrolments();
            boolean meeting = false;
            for (Enrolment enrolment: enrolments) {
                if (enrolment.getCourse() == preRequisite) {
                    if (enrolment.getGrade() == null)
                        return false;
                    if (enrolment.getGrade().compareTo("fail") == 0)
                        return false;
                    else {
                        meeting = true;
                        break;
                    }
                }
            }
            if (!meeting)
                return false;
        }
        return true;
    }

}
