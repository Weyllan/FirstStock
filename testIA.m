function IA_test()
  hold on;
    cova = 0;
    st= [];
    vt = [];
    res = [];
    i = 1;
  maxi = 0;
    a = 0;
    b = 0;
    point = [];

    
    while(true)
        disp("Entrée les stocks annuels : ");
    st(i) = input("");
    disp(["Je pense que tu vas écouler ", num2str(st(i)*a+b) ," produits "]);
    disp("Entrée vos ventes: ")
    vt(i) = input("");
    clf reset;
    hold on;
    res(i) = (st(i)-(sum(st))/length(st))*(vt(i)-(sum(vt))/length(vt));
    %cova = somme des (xi-moyenne des x)*(yi-moyenne des y) / nombre de i
    cova = sum(res);
    cova/=length(vt);
    cova
    ecartypest = 0;
    ecartypevt = 0;
    for j=(1:i)
      ecartypest += (st(j)-(sum(st)/length(st)))^2;
      ecartypevt += (vt(j)-(sum(vt)/length(vt)))^2;
    end
    cova
    ecartypest/=length(vt);
    ecartypevt/=length(vt);
    ecartypevt=sqrt(ecartypevt);
    ecartypest=sqrt(ecartypest);
    a = cova/(ecartypest*ecartypest);
    b = sum(vt)/length(vt)-a*(sum(st)/length(st));

    if maxi<st(i)
      maxi = st(i);
    end 
    maxi
    x=(0:maxi);
    plot(x,x*a+b);
    i+=1;
    a
    b
    vt
    st

    plot(st, vt,"r");
    for k=(1:length(vt))
      plot(st(k), vt(k),"r");
    end
        
    end 
end