package saiprojects.sai.com.fliptaskapplication.Model;

public class ApiData
{
    private String head;

    private String image;

    private String character;

    private Release release;

    private String tail;

    private String name;

    private String type;

    private String amiiboSeries;

    private String gameSeries;

    public String getHead ()
    {
        return head;
    }

    public void setHead (String head)
    {
        this.head = head;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getCharacter ()
    {
        return character;
    }

    public void setCharacter (String character)
    {
        this.character = character;
    }

    public Release getRelease ()
    {
        return release;
    }

    public void setRelease (Release release)
    {
        this.release = release;
    }

    public String getTail ()
    {
        return tail;
    }

    public void setTail (String tail)
    {
        this.tail = tail;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getAmiiboSeries ()
    {
        return amiiboSeries;
    }

    public void setAmiiboSeries (String amiiboSeries)
    {
        this.amiiboSeries = amiiboSeries;
    }

    public String getGameSeries ()
    {
        return gameSeries;
    }

    public void setGameSeries (String gameSeries)
    {
        this.gameSeries = gameSeries;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [head = "+head+", image = "+image+", character = "+character+", release = "+release+", tail = "+tail+", name = "+name+", type = "+type+", amiiboSeries = "+amiiboSeries+", gameSeries = "+gameSeries+"]";
    }
}
