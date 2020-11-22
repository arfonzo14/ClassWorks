using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace ExamenLiebre
{
    class Program
    {
        static int pasoTortuga = 0;
        static int pasoLiebre = 0;
        static bool carrera = true;
        static readonly object l = new object();
        static Random ransom = new Random();
        static bool ganoTort = false;

        static void correTortuga()
        {
            Thread.CurrentThread.Name = "tortuga";

            while (carrera)
            {

                lock (l)
                {
                    if (carrera)
                    {
                        int rand = ransom.Next(101);
                        pasoTortuga++;
                        Console.WriteLine("Tortuga lleva {0} pasos", pasoTortuga);


                        if (pasoLiebre == pasoTortuga && rand <= 50)
                        {
                            Console.WriteLine("La tortuga ha hecho ruido");
                            Monitor.Pulse(l);
                        }

                        if (pasoTortuga >= 25)
                        {
                            carrera = false;
                            ganoTort = true;
                            Monitor.Pulse(l);
                        }

                    }
                }
                Thread.Sleep(300);
            }
        }

        static void correLiebre()
        {
            Thread.CurrentThread.Name = "liebre";
            Thread dorm = new Thread(dormir);
            int rand;
            int ranSleep;
           
            while (carrera)
            {
                rand = ransom.Next(101);
                ranSleep = ransom.Next(2501);
               
                lock (l)
                {
                    if (carrera)
                    {
                        pasoLiebre += 6;

                        if (pasoLiebre > 25)
                        {
                            Console.WriteLine("Liebre ha llegado a la meta y hasta se ha pasado un poco!");
                        }
                        else
                        {
                            Console.WriteLine("Liebre lleva {0} pasos", pasoLiebre);
                        }

                        if (rand <= 60)
                        {
                            Console.WriteLine("Liebre está durmiendo");
                            Thread.Sleep(ranSleep);
                            dorm.Start();
                            dorm.IsBackground = true;
                            Monitor.Wait(l);
  
                        }

                        if (pasoLiebre >= 25)
                        {
                            pasoLiebre = 25;
                            carrera = false;
                            ganoTort = false;
                        }

                        if (pasoLiebre < 25)
                        {
                            Monitor.Wait(l);
                        }
                    }
                }
                Thread.Sleep(200);
            }
        }


        static void dormir()
        {
            Thread.Sleep(2500);

            lock (l)
            {
                Monitor.Pulse(l);
            }
        }

        static void Main(string[] args)
        {
            Thread hiloTor = new Thread(correTortuga);
            Thread hiloLiebr = new Thread(correLiebre);

            hiloTor.Start();
            hiloLiebr.Start();
 
            hiloLiebr.Join();
            hiloTor.Join();

            if (ganoTort)
            {
                Console.WriteLine("Ganó la  {0} , perdió la {1}", hiloTor.Name, hiloLiebr.Name);
            }
            else
            {
                Console.WriteLine("Ganó la  {0}, perdió la {1}", hiloLiebr.Name, hiloTor.Name);
            }


            Console.ReadKey();

        }
    }
}
