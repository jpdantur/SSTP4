%Analitical solution
dt = 0.001; %Try different values
m = 70;
k = 10000;
gama = 100;
tf=5;

x = 0:dt:tf;
y = exp(-(gama/(2*m))*x).*cos(sqrt(k/m - gama^2/(4*m^2))*x);
plot(x,y)
%%%