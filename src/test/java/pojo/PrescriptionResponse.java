package pojo;

import java.util.List;

public class PrescriptionResponse {
    List<Prescription> data;
    Pagination pagination;

    public List<Prescription> getData() {
        return data;
    }

    public void setData(List<Prescription> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
