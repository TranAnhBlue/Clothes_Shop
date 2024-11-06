package model;

/**
 * Represents the size of a product.
 */
public class Size {

    private int sid; // Size ID
    private String name; // Name of the size
    private String description; // Description of the size
    private Gender gender; // Gender associated with the size

    public Size() {
    }

    // Constructor
    public Size(int sid, String name, String description, Gender gender) {
        this.sid = sid;
        this.name = name;
        this.description = description;
        this.gender = gender;
    }


    // Getters and Setters
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Size{"
                + "sid=" + sid
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", gender=" + gender
                + '}';
    }
}
