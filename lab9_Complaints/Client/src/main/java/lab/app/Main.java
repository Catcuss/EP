package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

public class Main {
    private static final String BASE_URL = "http://localhost:8080/Server-1.0-SNAPSHOT/api/complaints";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        System.out.println("Wszystkie skargi:");
        List<ComplaintDTO> allComplaints = client.target(BASE_URL)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
        allComplaints.forEach(System.out::println);

        System.out.println("\nOtwarta skarga:");
        ComplaintDTO openComplaint = client.target(BASE_URL + "/152")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
        System.out.println(openComplaint);

        System.out.println("\nEdycja otwartej skargi");
        openComplaint.setStatus("close");
        Response editComplaint = client.target(BASE_URL + "/" + openComplaint.getId())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(openComplaint));
        if (editComplaint.getStatus() == 204) {
            System.out.println("Skarga została zaktualizowana");
        } else {
            System.out.println("Błąd : " + editComplaint.getStatus());
        }

        System.out.println("\nWszystkie otwarte skargi:");
        List<ComplaintDTO> allOpenComplaints = client.target(BASE_URL)
                .queryParam("status", "open")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
        allOpenComplaints.forEach(System.out::println);

        client.close();
    }

    // helper class
    public static class ComplaintDTO {
        private Long id;
        private LocalDate complaintDate;
        private String complaintText;
        private String author;
        private String status;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDate getComplaintDate() {
            return complaintDate;
        }

        public void setComplaintDate(LocalDate complaintDate) {
            this.complaintDate = complaintDate;
        }

        public String getComplaintText() {
            return complaintText;
        }

        public void setComplaintText(String complaintText) {
            this.complaintText = complaintText;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "ComplaintDTO{" +
                    "id=" + id +
                    ", complaintDate=" + complaintDate +
                    ", complaintText='" + complaintText + '\'' +
                    ", author='" + author + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}