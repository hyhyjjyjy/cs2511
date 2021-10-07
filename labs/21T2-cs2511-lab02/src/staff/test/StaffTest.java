package staff.test;

import staff.Lecturer;
import staff.StaffMember;

public class StaffTest {
    public static void main(String[] args) {
        StaffMember newStaff1 = new StaffMember("BunnyDong", 30000, "2020.5.1", "2022.3.3");
        StaffMember newLecturer1 = new Lecturer("BunnyDong", 30000, "2020.5.1",
                "2022.3.3", "Cse", 'A');
        StaffMember A = newStaff1;
        StaffMember B = newLecturer1;
        StaffMember C = newStaff1;
        StaffTest test = new StaffTest();
        test.printStaffDetails(newStaff1);
        test.printStaffDetails(newLecturer1);

        System.out.println("A == B is " + (A == B));
        System.out.println("B == C is " + (B == C));
        System.out.println("A == C is " + (A == C));
    }

    public void printStaffDetails(StaffMember newStaff) {
        System.out.println(newStaff);

    }

}
