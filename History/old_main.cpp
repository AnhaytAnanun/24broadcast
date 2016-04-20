#include <iostream>
#include <cassert>
#include <cmath>

bool print(int m, int v2, int v3, int v4, int v5, int v6,
        int v23, int v24, int v25, int v33, int v34, int v35, 
        int v44, int v45, int v55)
{
    static int c=0;
    std::cout<<++c<<" : m="<<m<<",v2="<<v2
        <<",v3="<<v3
        <<",v4="<<v4<<",v5="<<v5<<",max_degree="<<v6<<"\n";
    std::cout<<"v23="<<v23<<",v24="<<v24<<",v25="
        <<v25<<",v33="<<v33<<",v34="<<v34<<",v35="<<v35
        <<",v44="<<v44 <<",v45="<<v45 <<",v55="<<v55<<"\n";
}

void check_edges(int m, int v2, int v3, int v4, int v5, int v6, int max_degree)
{
    for (int v23=0;v23<=v2;++v23)
        for (int v24=0;v24<=2*v2-v23;++v24) {
            int v25 = 2*v2 -v23 - v24;
            for (int v33=0;2*v33<=3*v3-v23;++v33)
                for (int v34=0;v34<=3*v3-v23-2*v33;++v34) {
                    int v35 = 3*v3-v23-2*v33-v34;
                    for (int v44=0;2*v44<=4*v4-v24-v34;++v44){
                        int v45 = 4*v4-v24-v34-2*v44;
                        int v55 = m/2 - (v23+ v24 + v25 + v33 + v34 + v35 + v44 + v45);
                        if (v55 >= 0)
                        if (v23 <= v25)
                        if (v23 <= v3 + v34+v35)
                        if (v25 <= 4*v5 + (max_degree -1)*v6-std::ceil(v23 * 1.0 / (max_degree -2 ) ))
                        if (v45 + v55 >= std::ceil(v23 * 1.0 / (max_degree -2) ))
                        if (v24 <= 3*v4 - std::ceil( (v2-v25)* 1.0/(2)))
                            print(m, v2, v3, v4, v5, max_degree, v23, v24,
                                v25, v33, v34, v35, v44, v45, v55);
                        //}
                    }
                }
        }
}

int main()
{
    const int n = 24;
    const int M = 68; // change to 70 for 35 edges
    for (int v2=0;v2<=n;++v2)
        for (int v3=0;v3<=n-v2;++v3)
            for (int v4=0;v4<=n-v2-v3;++v4)
                for (int v5=0;v5<=n-v2-v3-v4;++v5) {
                int v6 = n-(v2+v3+v4+v5);
                if (v6 == 0) {
                    int m = 2*v2 + 3*v3 + 4*v4 + 5*v5;
                    if (m <= M)
                        check_edges(m,v2,v3,v4,v5,v6,5);
                } else {
                    for (int max_degree =6; max_degree <= n-1; ++max_degree) {
                        int m = 2*v2 + 3*v3 + 4*v4 + 5*v5 + 6*v6 + max_degree -6 ;
                        if (m <= M)
                        check_edges(m,v2,v3,v4,v5,v6,max_degree);
                    }
                }
            }
}

