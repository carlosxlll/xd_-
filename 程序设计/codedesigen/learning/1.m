function shili01

    h0=figure(‘toolbar’,’none’,…
    
    ‘position’,[198 56 350 300],…
    
    ‘name’,’实例01′);
    
    h1=axes(‘parent’,h0,…
    
    ‘visible’,’off’);
    
    x=-pi:0.05:pi;
    
    y=sin(x);
    
    plot(x,y);
    
    xlabel(‘自变量X’);
    
    ylabel(‘函数值Y’);
    
    title(‘SIN( )函数曲线’);
    
    grid on