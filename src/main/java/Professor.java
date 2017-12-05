import java.util.LinkedList;
import java.util.List;

public class Professor {
    Subject [] subjects;

    public Professor(int nsubject) {
        subjects = new Subject[nsubject];
        for(Subject s : subjects)
            s = new Subject();
    }

    public int overlaptimes()
    {
        List list = new LinkedList();
        int count = 0;
        for(int i = 0; i<subjects.length; i++) {
            int time = subjects[i].chromosome%16;
            if(list.contains(time))
                count++;
            if(time < 3)
            {
                if(list.contains(7))
                    count++;
            }
            else if(time < 5)
            {
                if(list.contains(8))
                    count++;
            }
            else if(time < 7)
            {
                if(list.contains(9))
                    count++;
            }
            else if(time == 7)
            {
                if(list.contains(1))
                    count++;
                if(list.contains(2))
                    count++;
            }
            else if(time == 8)
            {
                if(list.contains(3))
                    count++;
                if(list.contains(4))
                    count++;
            }
            else if(time == 9)
            {
                if(list.contains(5))
                    count++;
                if(list.contains(6))
                    count++;
            }
            list.add(time);
        }

        return count;
    }

    public boolean lectureOnFri()
    {
        for(int i = 0; i<subjects.length; i++)
        {
            int time = subjects[i].chromosome%16;
            if(time == 13 || time == 14)
                return true;
        }
        return false;
    }

    public boolean lectureOnMorning()
    {
        for(int i = 0; i<subjects.length; i++)
        {
            int time = subjects[i].chromosome%16;
            if(time%12 == 1)
                return true;
        }
        return false;
    }

    public boolean lectureOnLunch()
    {
        for(int i = 0; i<subjects.length; i++)
        {
            int time = subjects[i].chromosome%16;
            if(time == 3)
                return true;
        }
        return false;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Subject s : subjects) {
            sb.append(s+"\r\n");
        }
        return sb.toString();
    }


}
