package model;

public enum SecurityQuestion {
    FATHERS_NAME("What is your father’s name?"),
    FAVOURITE_COLOR("What is your favourite color?"),
    FIRST_PET("What was the name of your first pet?");

    private String question;

    SecurityQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
