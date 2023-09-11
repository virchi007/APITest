package API;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ColorsData {
    public Integer id;
    public String name;
    public Integer year;
    public String color;
    public String pantone_value;

    @JsonCreator
    public ColorsData( @JsonProperty("id")Integer id,  @JsonProperty("nsme")String name,
                       @JsonProperty("year")Integer year,  @JsonProperty("color")String color,
                       @JsonProperty("value")String pantone_value) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantone_value = pantone_value;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPantone_value(String pantone_value) {
        this.pantone_value = pantone_value;
    }

    public String getPantone_value() {
        return pantone_value;
    }
}
