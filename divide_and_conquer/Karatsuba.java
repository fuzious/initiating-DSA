class Solution {
    public String multiply(String num1, String num2) {
        
        // rendering to actual numbers for multiplication
        String type = "unequal";
        
        int l1 = num1.length();
        int l2 = num2.length();
        String feed = addstartzeros(num1, num2);
        if(l1<l2) num1 = feed;
        if(l1>l2) num2 = feed;
        
       String ans = dudejustmultiply(num1, num2);  
      
       //if(type=="unequal") {
           while(ans.charAt(0)=='0' && ans.length()>1){
               ans = ans.substring(1);
           } 
       
        return ans;
        
    }
    
  
    public String dudejustmultiply(String num1, String num2){
        
        if(num1.length()==1 || num2.length()==1) {  // incase other number in string is greater than max integer value
            String max=num1;
            String min=num2;
            
            if(num1.length() ==1) {max = num2; min = num1;}
            String sum = "0";
            for(int i=0;i<Integer.parseInt(min);i++){
                sum = addnum(sum,max);
            }
            // System.out.println(sum);
            return sum;
        }

        
        String num1l = num1.substring(0,num1.length()/2);
        String num1r = num1.substring(num1.length()/2,num1.length());
        
        int l1 = num1l.length();
        int r1 = num1r.length();
        
        
        String num2l = num2.substring(0,num2.length()/2);
        String num2r = num2.substring(num2.length()/2,num2.length());
        
        int l2 = num2l.length();
        int r2 = num2r.length();
        

        if(l1!=l2) {
            String s = addstartzeros(num1l, num2l);
            if(l1<l2) num1l = s;
            else num2l = s;
        } 
        String p = dudejustmultiply(num1l , num2l);
        
        if(r1!=r2) {
            String sd = addstartzeros(num1r, num2r);
            if(r1<r2) num1r = sd;
            else num2r = sd;
        } 
        String r = dudejustmultiply(num1r,num2r);
        
        // for subtraction
        String add1 = addnum(num1l,num1r);
        String add2 = addnum(num2l,num2r);
        int lad1 = add1.length();
        int lad2 = add2.length();
        if(lad1!=lad2) {
            String slad = addstartzeros(add1, add2);
            if(lad1<lad2) add1 = slad;
            else add2 = slad;
        }
            
        String q = subtract(dudejustmultiply(add1 , add2) , addnum(p,r));
        
        int l = num1.length();
        
        if(l%2 != 0) {int k = l/2; 
                      return addnum(addendzeros(p, 2*(k+1)) , addnum(addendzeros(q,(k + 1)) , r) );
                     }
        
        return addnum(addendzeros(p,l) , addnum(addendzeros(q,l/2) , r));
    }
    
    
    public String addendzeros(String a , int num){
        for(int i=0;i<num;i++){
            a = a + "0";
        }
        return a;
    }

    public String addnum(String a, String b){
        int carry=0;
        int la = a.length();
        int lb = b.length();
        String t = addstartzeros(a,b);
        if(la<lb) a = t;
        if(lb<la) b = t;
        String ans="";
        for(int i=a.length()-1;i>=0;i--){
            int dummy = carry + Character.getNumericValue(a.charAt(i)) + Character.getNumericValue(b.charAt(i));
            if(dummy >= 10) carry = 1; 
            else carry=0;
            ans = String.valueOf(dummy%10) + ans;
            
        }
        if(carry!=0) ans = String.valueOf(carry) + ans;
        return ans;
    }
    
    public String subtract(String a, String b){
        
        int la = a.length();
        int lb = b.length();
        String dum = addstartzeros(a,b);
        if(la<lb) a = dum;
        if(lb<la) b = dum;
        String ans="";
        int i;
        int carry=0;
        boolean exchange=false;
        if(Character.getNumericValue(a.charAt(0))<Character.getNumericValue(b.charAt(0))) {String temp=b;b=a;a=temp;exchange=true;}
        
        for(i=a.length()-1;i>=0;i--){
            
            int aint = Character.getNumericValue(a.charAt(i));
            int bint = Character.getNumericValue(b.charAt(i));
            aint = carry + aint;
            if(aint < bint) {
                ans  = String.valueOf(10+aint-bint) + ans; 
                carry = -1;
            }
            else {
                ans = String.valueOf(aint-bint) + ans; 
                carry=0;
            }
        }
        
        if(exchange==true) ans = "-" + ans;
        return ans;
    }
    
    public String addstartzeros(String a, String b){
        
        
        int l1 = a.length();
        int l2 = b.length();
        int num =  Math.abs(l1-l2);

        if(l1>l2){
            
            for(int i=0;i<num;i++){
                b = "0" + b;
            }
            return b;
        }
        
        else{
             
            for(int i=0;i<num;i++){
                a = "0" + a;
            }
             return a;
        }

       
    }
    
}
        
        