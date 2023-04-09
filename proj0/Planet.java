public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos) + (yyPos - p.yyPos)*(yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        return G*mass*p.mass/(r * r);
    }

    public double calcNetForceExertedByX(Planet p){
        double dx = p.xxPos - xxPos;
        return calcForceExertedBy(p) * dx / calcDistance(p);
    }

    public double calcNetForceExertedByY(Planet p){
        double dy = p.yyPos - yyPos;
        return calcForceExertedBy(p) * dy / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanet){
        double totalXforce = 0;
        for(Planet p : allPlanet){
            if(this.equals(p)){
                continue;
            }
            totalXforce = totalXforce + calcNetForceExertedByX(p);
        }
        return totalXforce;
    }

    public double calcNetForceExertedByY(Planet[] allPlanet){
        double totalYforce = 0;
        for(Planet p : allPlanet){
            if(this.equals(p)){
                continue;
            }
            totalYforce = totalYforce + calcNetForceExertedByY(p);
        }
        return totalYforce;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
        return;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
    }

}
