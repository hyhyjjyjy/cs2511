package unsw.piazza;

import java.util.List;
import java.util.ArrayList;
/**
 * A Piazza Forum 
 * 
 * @author Your Name
 */
public class PiazzaForum {
    private String classname;
    List<Thread> published = new ArrayList<Thread>();

    /**
     * Initialises the new PiazzaForum with the given group name
     */
    public PiazzaForum(String className) {
        this.classname = className;
    }

    /**
     * @return The name of the forum
     */
    public String getName() {
        return this.classname;
    }

    /**
     * Sets the name of the group of the Forum
     * @param name
     */
    public void setName(String name) {
        this.classname = name;
    }

    /**
     * Returns a list of Threads in the Forum, in the order that they were published
     */
    public List<Thread> getThreads() {
        return this.published;
    }

    /**
     * Creates a new thread with the given title and adds it to the Forum.
     * The content is provided to allow you to create the first Post.
     * Threads are stored in the order that they are published.
     * Returns the new Thread object
     * @param title
     * @param content
     */
    public Thread publish(String title, String content) {
        Thread new_Thread = new Thread(title, content);
        this.published.add(new_Thread);
        return new_Thread;
    }

    /**
     * Searches all forum Threads for any that contain the given tag.
     * Returns a list of all matching Thread objects in the order that they were published.
     * @param tag
     * @return
     */
    public List<Thread> searchByTag(String tag) {
        List<Thread> allThreads = new ArrayList<Thread>();
        for (Thread anyThread: this.published) {
            if (anyThread.getTags().contains(tag) == true)
                allThreads.add(anyThread);
        }
        return allThreads;
    }

}