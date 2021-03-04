#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "a2_helper.h"
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <semaphore.h>
#include <pthread.h>
#include <stdlib.h>

sem_t sem; //semafor pentru sincronizarea threadurilor din acelasi proces
sem_t sem2; //semafor pentru sincronizarea threadurilor din procese diferite
sem_t sem3; //semafor pentru sincronizarea threadurilor din procese diferite
sem_t logSem; //semafor pentru formarea barierei de 6 threaduri 
sem_t sem4; //semafor pentru cele 46 de threaduri din procesul 3 (in afara de threadul 10)
sem_t sem5; //semafor pentru cele 46 de threaduri din procesul 3 (in afara de threadul 10)
sem_t sem6; //semafor pentru controlul numarului de threaduri in timpul rularii acestora

int nrThreads;

//wait
void P(sem_t *sem)
{
    sem_wait(sem);
}

//post
void V(sem_t *sem)
{
    sem_post(sem);
}

void* thread_function(void* arg){

    int* thread = (int*)arg;

    if(*thread == 4)
    {
        info(BEGIN, 7, *thread); 
        V(&sem); 
    }
    else
    {
        if(*thread == 2)
        {
            P(&sem);
            info(BEGIN, 7, *thread);
            info(END, 7, *thread);
            info(END, 7, 4);
        }
        else
        {
            if(*thread != 3 && *thread != 4 && *thread !=2)
            {
                info(BEGIN, 7, *thread);
                info(END, 7, *thread);
            }
        }
    }

    return NULL;
}

void* thread_function_2(void* arg){
    
    int* thread = (int*)arg;

    if(*thread == 10)
    {
        P(&logSem); //se asteapta cele 6 procese

        info(BEGIN, 3, *thread);

        for(int i = 0; i < 46; i++) //se forteaza trezirea celor 46 de threaduri dupa ce porninm threadul 10
        {
            V(&sem4);
        }

        for(int i = 0; i < 5; i++) //adormim procesul 10 pana cand exista 6 threaduri care merg simultan
        {
            P(&sem6);
        }

        info(END, 3, *thread); 

        for(int i = 0; i < 46; i++) 
        {
            V(&sem5);
        }

        V(&logSem);
    }
    else
    {
        P(&sem4); //threadurile dorm pana cand apare threadul 10
        P(&logSem); //pastreaza nr de 6 threaduri active constant
        
        info(BEGIN, 3, *thread); 
        
        if(nrThreads < 6)
        {
            V(&sem6); //se trezeste threadul 10
            nrThreads = nrThreads + 1;
        }

        P(&sem5); 
        info(END, 3, *thread);
        V(&logSem);
    }

    return NULL;
}

void* thread_function_3(void* arg){

    int* thread = (int*)arg;

    if(*thread == 4)
    {
        info(BEGIN, 8, *thread);
        V(&sem2);
    }
    else
    {
        if(*thread == 3)
        {
            P(&sem2);
            info(END, 8, 4);

            info(BEGIN, 8, 3);
            info(END, 8, 3);

            info(BEGIN, 7, *thread);
            V(&sem3);
        }
        else
        {
            if(*thread == 5)
            {
                P(&sem3);
                info(END, 7, 3);

                info(BEGIN, 8, *thread);
                info(END, 8, *thread);
            }
            else
            {
                info(BEGIN, 8, *thread);
                info(END, 8, *thread);
            }
        }     
    }

    return NULL;
}

int main(){
    init();

    info(BEGIN, 1, 0);
    
    sem_init(&logSem, 0, 6);
    sem_init(&sem4, 0, 0);
    sem_init(&sem5, 0, 0);
    sem_init(&sem6, 0, 0);

    int p2 = fork();
   

    switch (p2)
    {
        case 0: //cod p2
            info(BEGIN,2,0);
            int p3 = fork();

            switch (p3)
            {
                case 0: //cod p3
                    info(BEGIN, 3, 0);
                    nrThreads = 1;
                    int* aux47 = malloc(47 * sizeof(int)); 
                    pthread_t* th47 = malloc(47 * sizeof(pthread_t));

                    for(int i = 0; i < 47; i++)
                    {
                        aux47[i] = i+1;
                    }

                    for(int i = 0; i < 47; i++)
                    {
                        pthread_create(&th47[i], NULL, thread_function_2, &aux47[i]);
                    }

                    for(int i = 0; i < 47; i++)
                    {
                        pthread_join(th47[i], NULL);
                    }

                    free(th47);
                    free(aux47);
                    int p5 = fork();
                    
                    switch (p5)
                    {
                        case 0: //cod p5
                            info(BEGIN, 5, 0);
                            info(END, 5, 0);
                            break;
                    
                        default: //cod p3
                            wait(NULL);
                            info(END, 3, 0);
                            break;
                    }
                    break;
            
                default:; //cod p2
                    int p4 = fork();

                    switch (p4)
                    {
                        case 0: //cod p4
                            info(BEGIN, 4, 0);
                            info(END, 4, 0);
                            break;
                    
                        default:; //cod p2
                            int p6 = fork();

                            switch (p6)
                            {
                                case 0: //cod p6
                                    info(BEGIN, 6, 0);
                                    int p7 = fork();

                                    switch (p7)
                                    {
                                        case 0: //cod p7
                                            info(BEGIN, 7, 0);

                                            int* aux4 = malloc(4 * sizeof(int));
                                            pthread_t* th4 = malloc(4 * sizeof(pthread_t));

                                            for(int i = 0; i < 4; i++)
                                            {
                                                aux4[i] = i+1;
                                            }

                                            for(int i = 0; i < 4; i++)
                                            {
                                                pthread_create(&th4[i], NULL, thread_function, &aux4[i]);
                                            }

                                            for(int i = 0; i < 4; i++)
                                            {
                                                pthread_join(th4[i], NULL);
                                            }

                                            free(th4);
                                            free(aux4);
                                            info(END, 7, 0);
                                            break;
                                    
                                        default:; //cod p6
                                            int p8 = fork();

                                            switch (p8)
                                            {
                                                case 0: //cod p8
                                                    info(BEGIN, 8, 0);

                                                    int* aux6 = malloc(6 * sizeof(int));
                                                    pthread_t* th6 = malloc(6 * sizeof(pthread_t));

                                                    for(int i = 0; i < 6; i++)
                                                    {
                                                        aux6[i] = i+1;
                                                    }

                                                    for(int i = 0; i < 6; i++)
                                                    {
                                                        pthread_create(&th6[i], NULL, thread_function_3, &aux6[i]);
                                                    }

                                                    for(int i = 0; i < 6; i++)
                                                    {
                                                        pthread_join(th6[i], NULL);
                                                    }

                                                    free(th6);
                                                    free(aux6);

                                                    info(END, 8, 0);
                                                    break;
                                            
                                                default: //cod p6
                                                    wait(NULL);
                                                    wait(NULL);
                                                    info(END, 6, 0);
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            
                                default: //cod p2
                                    wait(NULL);
                                    wait(NULL);
                                    wait(NULL);
                                    info(END, 2, 0);
                                    break;
                            }
                            break;
                    }
                    break;
            }

            break;
    
        default: //cod p1
            wait(NULL);

            info(END, 1, 0);
            
            break;
    }
    sem_destroy(&logSem);
    sem_destroy(&sem4);
    sem_destroy(&sem5);
    sem_destroy(&sem6);

    return 0;
}
