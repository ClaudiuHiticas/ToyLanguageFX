package Model;

public class Generator {
    private Integer generatedNumber;
    public Generator(){
        this.generatedNumber = 0;
    }
    Integer getGeneratedNumber(){
        this.generatedNumber++;
        return this.generatedNumber;
    }

}
