package com.example.c.t30_criminalintent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by c on 2015-08-22.
 */
public class CrimeLab {
    private ArrayList<Crime> mCrimes;
    private static CrimeLab sCrimeLab;

    public static CrimeLab get(){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab();
        }
        return sCrimeLab;
    }

    public CrimeLab(){
        mCrimes = new ArrayList<Crime>();
        for(int i = 0; i < 100; i++){
            Crime c = new Crime();
            c.setTitle("범죄 " + i);

/*            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
            or
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22*/

            Date today = Calendar.getInstance().getTime();
            c.setDate(today);
            c.setSolved(i%2==0);
            mCrimes.add(c);
        }
    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for(Crime c: mCrimes){
            if(c.getId().equals(id))
                return c;
        }
        return null;
    }
}
