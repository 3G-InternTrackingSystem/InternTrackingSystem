
public class GeneralEvents extends Task {

    //Properties
    private int duration;
    private Date date;
    private String description;

    public GeneralEvents (String name, Date date, int duration, String description, Date dueDate){

        super( String name, Date dueDate ) {
            this.duration= duration;
            this.date= date;
            this. description = description;

        }

    }

      public int getDuration() {
        return duration;
      }

      public String getDescription() {
        return description;
      }

      public Date getDate() {
        return date;
      }


}
