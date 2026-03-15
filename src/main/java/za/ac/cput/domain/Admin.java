package za.ac.cput.domain;

public class Admin {

    private String id;
    private  String role;

    private Admin() {

    }

    private Admin(Builder builder) {
        this.id = builder.id;
        this.role = builder.role;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }


    @Override
    public String toString() {
        return "==Admin Details=={" +
                "\nId='" + id +
                "\nRole='" + role +
                '}';
    }

    public static class Builder {

        private String id;
        private String role;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder copy(Admin admin) {
            this.id = admin.id;
            this.role = admin.role;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }


}