package learning.learning.Utilitiess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntityResponse <T> {

    private String messages;
    private T entity;
    private  Integer statusCode;


}
