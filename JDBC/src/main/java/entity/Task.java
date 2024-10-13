package entity;

import java.util.Date;

public record Task (
        Short id,
        String title,
        String description,
        Date createdAt
) {
}
