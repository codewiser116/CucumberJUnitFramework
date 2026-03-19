package pojo;

import java.util.List;

public class AppointmentResponse {

    private List<Appointment> data;
    private Pagination pagination;

    public List<Appointment> getData() {
        return data;
    }

    public void setData(List<Appointment> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
