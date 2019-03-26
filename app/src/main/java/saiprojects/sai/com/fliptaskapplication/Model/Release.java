package saiprojects.sai.com.fliptaskapplication.Model;

public class Release
{
    private String eu;

    private String na;

    private String au;

    private String jp;

    public String getEu ()
    {
        return eu;
    }

    public void setEu (String eu)
    {
        this.eu = eu;
    }

    public String getNa ()
    {
        return na;
    }

    public void setNa (String na)
    {
        this.na = na;
    }

    public String getAu ()
    {
        return au;
    }

    public void setAu (String au)
    {
        this.au = au;
    }

    public String getJp ()
    {
        return jp;
    }

    public void setJp (String jp)
    {
        this.jp = jp;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [eu = "+eu+", na = "+na+", au = "+au+", jp = "+jp+"]";
    }
}
