package za.ac.cput.domain;


public class Subject {
    private String subjectCode;
    private String subjectName;
    private String subjectDescription;
    private String gradeLevel;

    private Subject() {
    }

    public Subject(Builder bulider){
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.gradeLevel = gradeLevel;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectDescription='" + subjectDescription + '\'' +
                ", gradeLevel='" + gradeLevel + '\'' +
                '}';
    }

    public static class Builder {
        private String subjectCode;
        private String subjectName;
        private String subjectDescription;
        private String gradeLevel;

        public void setSubjectCode(String subjectCode) {
            this.subjectCode = subjectCode;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public void setSubjectDescription(String subjectDescription) {
            this.subjectDescription = subjectDescription;
        }

        public void setGradeLevel(String gradeLevel) {
            this.gradeLevel = gradeLevel;
        }
        public Subject.Builder copy(Subject subject){
            this.subjectCode = subject.subjectCode;
            this.subjectName = subject.subjectName;
            this.subjectDescription = subject.subjectDescription;
            this.gradeLevel = subject.gradeLevel;
            return this;
        }
        public Subject bulid(){
            return new Subject(this);
        }
    }
}

