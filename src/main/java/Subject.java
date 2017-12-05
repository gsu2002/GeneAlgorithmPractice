
public class Subject {
    byte code;
    int chromosome;    // classroom(4bit) + time(4bit)

    public Subject() {
    }

    public Subject(byte code) {
        this.code = code;
        chromosome = createChromosome();

    }

    public Subject(byte code, byte chromosome) {
        this.code = code;
        this.chromosome = chromosome;
    }

    public int createChromosome()
    {
        int bytes;
        do {
            int classroom = (int) (Math.random() * 9);    // 0 ~ 8
            int time = (int) (Math.random() * 14 + 1);    // 1 ~ 14
            bytes = classroom * 16 + time;
        }while (!validChromosome(bytes));
        return bytes;
    }

    public boolean validChromosome(int b)
    {

        if(b%16 > 0 && b%16 < 15)               //time
            if (b/16 < 9) {                     //classroom
                if (code > 3)  // 실습과목
                {
                    if (b / 16 < 3) // 실습실
                    {
                        if(b%16 > 6)
                            return true;
                    }
                    else
                        return false;
                }
                else           // 이론과목
                {
                    if (b / 16 >= 3) // 강의실
                        return true;
                    else
                        return false;
                }
            }
        return false;
    }

    public boolean crossover(Subject subject)
    {
        int classroom = chromosome/16;
        int time = chromosome%16;
        int classroom1 = subject.chromosome/16;
        int time1 = subject.chromosome%16;

        // 강의실 교차
        if(Math.random() > 0.5) {
            int chrom = classroom1 * 16 + time;
            int chrom1 = classroom * 16 + time1;
            if (validChromosome(chrom1) && subject.validChromosome(chrom)) {
                chromosome = chrom1;
                subject.chromosome = chrom;
                return true;
            }
            return false;
        }
        else
        {   // 수업시간 교차
            int chrom = classroom * 16 + time1;
            int chrom1 = classroom1 * 16 + time;
            if (validChromosome(chrom1) && subject.validChromosome(chrom)) {
                chromosome = chrom1;
                subject.chromosome = chrom;
                return true;
            }
            return false;
        }


//        String str = Integer.toBinaryString(chromosome);
//        int length = 8 - str.length();
//        String zero = "";
//        for(int i = 0; i<length; i++)
//            zero+="0";
//        str = zero+str;
//
//        String str1 = Integer.toBinaryString(subject.chromosome);
//        length = 8 - str1.length();
//        zero = "";
//        for(int i = 0; i<length; i++)
//            zero+="0";
//        str1 = zero+str1;
//
//        int rd = (int)(Math.random() * 8);
//        int rd1 =(int)(Math.random() * 8);
//        if(str.charAt(rd) == str1.charAt(rd))
//            return false;
//        if(str.charAt(rd1) == str1.charAt(rd1))
//            return false;
//        else
//        {
//            int chrom1 = chromosome;
//            int chrom2 = subject.chromosome;
//            int base = (int)(Math.pow(2, 8-rd-1));
//            if(str.charAt(rd) == '1')
//            {
//                chrom1 -= base; // chrom1's base bit = 1
//                chrom2 += base; // chrom2's base bit = 0
//            }
//            else
//            {
//                chrom1 += base; // chrom1's base bit = 0
//                chrom2 -= base; // chrom2's base bit = 1
//            }
//
//            base = (int)(Math.pow(2, 8-rd1-1));
//            if(str.charAt(rd) == '1')
//            {
//                chrom1 -= base; // chrom1's base bit = 1
//                chrom2 += base; // chrom2's base bit = 0
//            }
//            else
//            {
//                chrom1 += base; // chrom1's base bit = 0
//                chrom2 -= base; // chrom2's base bit = 1
//            }
//
//            if(validChromosome(chrom1) && subject.validChromosome(chrom2))
//            {
//                chromosome = chrom1;
//                subject.chromosome = chrom2;
//                return true;
//            }
//            return false;
//        }
    }

    public boolean mutate()
    {
        int classroom = (int) (Math.random() * 9);
        int time = (int) (Math.random() * 14 + 1);
        int b = classroom * 16 + time;
        if(validChromosome(b)) {
            chromosome = b;
            return true;
        }
        return false;
//        String str = Integer.toBinaryString(chromosome);
//        int length = 8 - str.length();
//        String zero = "";
//        for(int i = 0; i<length; i++)
//            zero+="0";
//        str = zero+str;
//        int b;
//        b = chromosome;
//        int rd = (int)(Math.random() * 8);
//        int rd1 = (int)(Math.random() * 8);
//        String temp = new String(str.toString());
//        if(temp.charAt(rd) == '1')
//            b -= Math.pow(2, 8-rd-1);
//        else
//            b += Math.pow(2, 8-rd-1);
//        if(temp.charAt(rd1) == '1')
//            b -= Math.pow(2, 8-rd-1);
//        else
//            b += Math.pow(2, 8-rd-1);
//        if(validChromosome(b)) {
//            chromosome = b;
//            return true;
//        }
//        return false;
    }

    @Override
    public String toString() {
        int classroom = chromosome/16;
        int time = chromosome%16;
        return "["+code+", "+classroom+", "+time+"] ";
    }
}
