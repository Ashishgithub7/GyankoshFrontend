package com.example.practice.Model_Test;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

//import java.util.ArrayList;
//import java.util.function.IntFunction;
//import java.util.stream.Stream;

public class ModelSetQuestions implements Parcelable {
    private Long qid;

    private String course;

    private String setName;

    private String question_text;

    private String option1;  // Option text

    private String option2;

    private String option3;

    private String option4;

    private String correctAnswer;  // Correct answer option text

    public ModelSetQuestions(Long qid, String course, String setName, String question_text, String option1, String option2, String option3, String option4, String correctAnswer) {
        this.qid = qid;
        this.course = course;
        this.setName = setName;
        this.question_text = question_text;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
    }

    protected ModelSetQuestions(Parcel in) {
        if (in.readByte() == 0) {
            qid = null;
        } else {
            qid = in.readLong();
        }
        course = in.readString();
        setName = in.readString();
        question_text = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        correctAnswer = in.readString();
    }

    public static final Creator<ModelSetQuestions> CREATOR = new Creator<ModelSetQuestions>() {
        @Override
        public ModelSetQuestions createFromParcel(Parcel in) {
            return new ModelSetQuestions(in);
        }

        @Override
        public ModelSetQuestions[] newArray(int size) {
            return new ModelSetQuestions[size];
        }
    };

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (qid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(qid);
        }
        dest.writeString(course);
        dest.writeString(setName);
        dest.writeString(question_text);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeString(correctAnswer);
    }
}

//    protected ModelSetQuestions(Parcel in) {
//        if (in.readByte() == 0) {
//            qid = null;
//        } else {
//            qid = in.readLong();
//        }
//        course = in.readString();
//        setName = in.readString();
//        question_text = in.readString();
//        option1 = in.readString();
//        option2 = in.readString();
//        option3 = in.readString();
//        option4 = in.readString();
//        correctAnswer = in.readString();
//    }
//    public static final Creator<ModelSetQuestions> CREATOR = new Creator<ModelSetQuestions>() {
//        @Override
//        public ModelSetQuestions createFromParcel(Parcel in) {
//            return new ModelSetQuestions(in);
//        }
//
//        @Override
//        public ModelSetQuestions[] newArray(int size) {
//            return new ModelSetQuestions[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(@NonNull Parcel dest, int flags) {
//        if (qid == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeLong(qid);
//        }
//        dest.writeString(course);
//        dest.writeString(setName);
//        dest.writeString(question_text);
//        dest.writeString(option1);
//        dest.writeString(option2);
//        dest.writeString(option3);
//        dest.writeString(option4);
//        dest.writeString(correctAnswer);
//    }
//
//}



//        package com.example.practice.Model_Test;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import androidx.annotation.NonNull;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.function.IntFunction;
//import java.util.stream.Stream;
//
//import javax.annotation.Generated;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//        "qid",
//        "course",
//        "setName",
//        "question_text",
//        "option1",
//        "option2",
//        "option3",
//        "option4",
//        "correctAnswer"
//})
//@Generated("jsonschema2pojo")
//public class ModelSetQuestions extends ArrayList<Parcelable> implements Parcelable {
//
//    @JsonProperty("qid")
//    private Integer qid;
//    @JsonProperty("course")
//    private String course;
//    @JsonProperty("setName")
//    private String setName;
//    @JsonProperty("question_text")
//    private String questionText;
//    @JsonProperty("option1")
//    private String option1;
//    @JsonProperty("option2")
//    private String option2;
//    @JsonProperty("option3")
//    private String option3;
//    @JsonProperty("option4")
//    private String option4;
//    @JsonProperty("correctAnswer")
//    private String correctAnswer;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

//    @JsonProperty("qid")
//    public Integer getQid() {
//        return qid;
//    }
//
//    @JsonProperty("qid")
//    public void setQid(Integer qid) {
//        this.qid = qid;
//    }
//
//    @JsonProperty("course")
//    public String getCourse() {
//        return course;
//    }
//
//    @JsonProperty("course")
//    public void setCourse(String course) {
//        this.course = course;
//    }
//
//    @JsonProperty("setName")
//    public String getSetName() {
//        return setName;
//    }
//
//    @JsonProperty("setName")
//    public void setSetName(String setName) {
//        this.setName = setName;
//    }
//
//    @JsonProperty("question_text")
//    public String getQuestionText() {
//        return questionText;
//    }
//
//    @JsonProperty("question_text")
//    public void setQuestionText(String questionText) {
//        this.questionText = questionText;
//    }
//
//    @JsonProperty("option1")
//    public String getOption1() {
//        return option1;
//    }

//    @JsonProperty("option1")
//    public void setOption1(String option1) {
//        this.option1 = option1;
//    }
//
//    @JsonProperty("option2")
//    public String getOption2() {
//        return option2;
//    }
//
//    @JsonProperty("option2")
//    public void setOption2(String option2) {
//        this.option2 = option2;
//    }
//
//    @JsonProperty("option3")
//    public String getOption3() {
//        return option3;
//    }
//
//    @JsonProperty("option3")
//    public void setOption3(String option3) {
//        this.option3 = option3;
//    }
//
//    @JsonProperty("option4")
//    public String getOption4() {
//        return option4;
//    }
//
//    @JsonProperty("option4")
//    public void setOption4(String option4) {
//        this.option4 = option4;
//    }
//
//    @JsonProperty("correctAnswer")
//    public String getCorrectAnswer() {
//        return correctAnswer;
//    }
//
//    @JsonProperty("correctAnswer")
//    public void setCorrectAnswer(String correctAnswer) {
//        this.correctAnswer = correctAnswer;
//    }

//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(@NonNull Parcel dest, int flags) {

//    }
//
//    @NonNull
//    @Override
//    public <T> T[] toArray(@NonNull IntFunction<T[]> generator) {
//        return super.toArray(generator);
//    }
//
//    @NonNull
//    @Override
//    public Stream<Parcelable> stream() {
//        return super.stream();
//    }
//
//    @NonNull
//    @Override
//    public Stream<Parcelable> parallelStream() {
//        return super.parallelStream();
//    }
//}
