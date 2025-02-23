Hi there!
We made this simulator for a college work assigned to us by professor Vinod Rebello from Universidade Federal Fluminense. It simulates a MIC-1 processor, proposed by Andrew Tanenbaum in his book Structured Computer Organization.
We tried to do a simulation as close as possible to the real thing and we achieved a pretty good result. One of the few differences is in the components of the data path. In the real machine, they operate all the time, but to avoid performance issues we decided to activate them only in certains moments of the simulation.
Features:
The simulator, as of now, includes:
  1- Interface that allows the visualization of the currently running program, system memory, registers, current microinstruction and subcycle;
  2- Simulation pause;
  3- Integrated assembler, allowing you to paste assembly code directly into the simulator and run it;
  4- Exibition of the execution time, updated after each cycle. (Not on the release yet, only in the most recent commits)
To use the assembler without problems, you should follow the syntax we specified:
  Flags (labels) should be declared befor the line, with only ":" separating it from the instruction;
  Variable and label names should have at least one character that is not a number;
  Don't put spaces after of before the lines;
  Separate variables and label usages from the instruction with one (and only one) space;
  Don't put commentaries, as they are not supported yet.
Example code:
  START:LOCO 16
  STOD var1
  LOCO 1
  STOD var2
  LOCO 0
  PUSH
  LOCO 1
  PUSH
  LOOP:LODL 1
  ADDL 0
  PUSH
  LODD var1
  SUBD var2
  STOD var1
  JZER END
  JUMP LOOP
  END:JUMP END
The program above calculates the numbers of the Fibonacci sequence up to the 16th number and stores them in the processor stack
Big thanks to Allan Gaetani (flafmg, https://github.com/flafmg/) for testing and making the first assemblers we used to test the simulator.
