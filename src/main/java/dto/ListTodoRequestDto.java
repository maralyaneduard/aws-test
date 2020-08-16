package dto;

import java.io.Serializable;

public class ListTodoRequestDto implements Serializable {
    private static final long serialVersionUID = -374681669473031823L;

    private Integer excludeCompleted;

    public Integer getExcludeCompleted() {
        return excludeCompleted;
    }

    public void setExcludeCompleted(Integer excludeCompleted) {
        this.excludeCompleted = excludeCompleted;
    }

    @Override
    public String toString() {
        return "ListTodoRequestDto{" +
                "excludeCompleted=" + excludeCompleted +
                '}';
    }
}
