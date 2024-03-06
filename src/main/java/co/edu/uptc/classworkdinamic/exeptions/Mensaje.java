package co.edu.uptc.classworkdinamic.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje {
    private int code;
    private String message;
    private int codeHttp;

}
