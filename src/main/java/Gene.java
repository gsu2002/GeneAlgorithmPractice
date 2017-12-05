import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Gene {
    Professor [] professors;
    LinkedList<Subject> subjectArrayList;
    LinkedList<Integer> [] classrooms = new LinkedList[9];
    LinkedList<Integer> [] levels = new LinkedList[4];
    int fit;

    public Gene() {
        professors = new Professor [14];
        int [] classes = {4,3,4,3,2,4,2,3,4,3,3,1,3,1};
        int i = 0;
        int j = 0;
        // 0, 권호열 4
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)1);
        professors[i].subjects[j++] = new Subject((byte)6);
        professors[i].subjects[j++] = new Subject((byte)6);
        professors[i].subjects[j++] = new Subject((byte)1);
        i++;
        j = 0;

        //1, 김만배 3
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)1);
        professors[i].subjects[j++] = new Subject((byte)0);
        professors[i].subjects[j++] = new Subject((byte)1);
        i++;
        j = 0;

        //2, 김용석 4
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)5);
        professors[i].subjects[j++] = new Subject((byte)2);
        professors[i].subjects[j++] = new Subject((byte)2);
        professors[i].subjects[j++] = new Subject((byte)0);
        i++;
        j = 0;

        //3, 김학수 3
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)4);
        professors[i].subjects[j++] = new Subject((byte)5);
        professors[i].subjects[j++] = new Subject((byte)1);
        i++;
        j = 0;

        //4, 김화종 2
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)5);
        professors[i].subjects[j++] = new Subject((byte)1);
        i++;
        j = 0;

        //5, 석호식 4
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)6);
        professors[i].subjects[j++] = new Subject((byte)6);
        professors[i].subjects[j++] = new Subject((byte)3);
        professors[i].subjects[j++] = new Subject((byte)1);
        i++;
        j = 0;

        //6, 이구연 2
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)3);
        professors[i].subjects[j++] = new Subject((byte)0);
        i++;
        j = 0;

        //7, 이헌길 3
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)5);
        professors[i].subjects[j++] = new Subject((byte)5);
        professors[i].subjects[j++] = new Subject((byte)0);
        i++;
        j = 0;

        //8, 정인범 4
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)5);
        professors[i].subjects[j++] = new Subject((byte)7);
        professors[i].subjects[j++] = new Subject((byte)7);
        professors[i].subjects[j++] = new Subject((byte)1);
        i++;
        j = 0;

        //9, 정충교 3
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)5);
        professors[i].subjects[j++] = new Subject((byte)5);
        professors[i].subjects[j++] = new Subject((byte)1);
        i++;
        j = 0;

        //10, 최창열 3
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)1);
        professors[i].subjects[j++] = new Subject((byte)1);
        professors[i].subjects[j++] = new Subject((byte)1);
        i++;
        j = 0;

        //11, 최황규 1
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)1);
        i++;
        j = 0;

        //12, 하진영 3
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)6);
        professors[i].subjects[j++] = new Subject((byte)6);
        professors[i].subjects[j++] = new Subject((byte)3);
        i++;
        j = 0;

        //13, 황환규 1
        professors[i] = new Professor(classes[i]);
        professors[i].subjects[j++] = new Subject((byte)6);
        i++;
        j = 0;
    }

    public void fillList()
    {
        subjectArrayList = new LinkedList<Subject>();
        for(int i = 0; i<classrooms.length; i++)
            classrooms[i] = new LinkedList<Integer>();
        for(int i = 0; i<levels.length; i++)
            levels[i] = new LinkedList<Integer>();
        for(int i = 0; i<professors.length; i++)
            for(int j = 0; j<professors[i].subjects.length; j++) {
                Subject subject = professors[i].subjects[j];
                subjectArrayList.add(subject);
                int index = subject.code%4;
                int classroom = subject.chromosome/16;
                int time = subject.chromosome%16;
                levels[index].add(time);
                classrooms[classroom].add(time);
            }
    }

    public boolean crossover(Gene gene)
    {
        int r1 = (int)(Math.random()*subjectArrayList.size());
        int r2 = (int)(Math.random()*subjectArrayList.size());
        Subject s1 = subjectArrayList.get(r1);
        Subject s2 = gene.subjectArrayList.get(r2);
        boolean result = s1.crossover(s2);
        return result;
    }

    public boolean mutate()
    {
        Subject subject = (subjectArrayList.get((int)(Math.random()*subjectArrayList.size())));
        return subject.mutate();
    }

    public int overlapClassroom()   // 동시간대 중복되어 사용되는 강의실의 과목 수
    {
        int count = 0;
        for(int room = 0; room < classrooms.length; room++)
        {
            LinkedList<Integer> list = classrooms[room];
            for(int i = 0; i<list.size(); i++)
            {
                int time = list.get(i);
                for(int j = 0; j<list.size(); j++)
                {
                    if(time == list.get(j))
                        count++;
                }
                count--;

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
            }
        }
        return count/2;
    }

    public int overlapLevel()   // 동시간대 같은 수강학년의 과목 수
    {
        int count = 0;
        for(LinkedList<Integer> list : levels)
        {
            for(int i = 0; i<list.size(); i++)
            {
                if(i == 1)  // 전체 학년 대상
                    continue;
                int time = list.get(i);
                for(int j = 0; j<list.size(); j++)
                {
                    if(time == list.get(j))
                        count++;
                }
                count--;
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
            }
        }
        return count;
    }

    public int overlapProfessorSchedule()   // 수업 시간이 겹치는 교수들의 과목 수
    {
        int count = 0;
        for(Professor p : professors)
        {
            if(p.subjects.length < 2)
                continue;
            else
                count += p.overlaptimes();
        }
        return count;
    }

    public int lecturesOnFri()
    {
        int count = 0;
        int [] arr = {0, 3, 4, 5, 9 ,11};   // 권호열 김학수 김화종 석호식 정충교 최황규
        List list = Arrays.asList(arr);
        for(int i = 0; i<professors.length; i++)
        {
            if(list.contains(i))
            {
                if(professors[i].lectureOnFri())
                    count++;
            }
        }
        return count;
    }

    public int lecturesOnMonring()  // 김학수 : 3, 이헌길 : 7
    {
        int count = 0;
        if(professors[3].lectureOnMorning())
            count++;
        if(professors[7].lectureOnMorning())
            count++;
        return count;
    }

    public int lecturesOnLunch()
    {
        int count = 0;
        for(int i = 0; i<professors.length; i++)
        {
            if(i != 1)  // 김만배 : 1 빼고 모두 검사
            {
                if(professors[i].lectureOnLunch())
                    count++;
            }
        }
        return count;
    }

    public void calFit()
    {
        int sum1 = 4*overlapClassroom()+3*overlapProfessorSchedule()+lecturesOnFri()+lecturesOnMonring();
        int sum2 = overlapLevel()+lecturesOnLunch();
        fit = (200 - sum1 -sum2)/2;
    }

    public String toString()
    {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(Professor p : professors) {
            sb.append("{"+count+"}\r\n");
            count++;
            sb.append(p);
        }
        return sb.toString();
    }

    public Gene clone()
    {
        calFit();
        Gene gene = new Gene();
        gene.professors = professors.clone();
        gene.subjectArrayList = (LinkedList<Subject>) subjectArrayList.clone();
        gene.classrooms = classrooms.clone();
        gene.levels = levels.clone();
        gene.fit = fit;
        return gene;
    }


}
