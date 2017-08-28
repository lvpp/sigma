# GAMESS INSTRUCTIONS

## INSTALATION GUIDE

Currently we have instructions for Ubuntu Linux machines only.

First of all, you need to [download](http://www.msg.ameslab.gov/gamess/download.html) the **GAMESS** package.

You need to create a folder called **src** within your **home folder**. Unzip the downloaded **GAMESS** package inside
that folder. Make sure the final directory is called **/home/lvpp/gamess**, where **lvpp** should be your own username.

**Install the required packages**
```
sudo apt-get install scons csh gfortran
```

**Using a terminal, enter the GAMESS package folder and run the configuration script:**
```
cd $HOME/src/gamess
./config
```
This script will ask you several questions, typical answers for a Ubuntu Linux Desktop are:
 * Machine type: **linux64**
 * Directory: **$HOME/src/gamess**
 * FORTRAN compiler: **gfortran**
 * Standard math library: **none**, or another optimized library if you know what you're doing
 * Comunication library: **sockets**, as we will not rely on parallel computing 

**Enter the ddi folder, compile ddi, and copy to ../:**
```
cd $HOME/src/gamess/ddi
./compddi
cp ddikick.x ../
```

**Go back to the top folder and compile all modules:**
```
cd $HOME/src/gamess
./compall
```

**Finally link the modules:**
```
./lked
```

**Increase the shared memory maximum, add the following lines to the end of your /etc/sysctl.conf**
```
# gamess shmem
kernel.shmmax=560525368
```

You probably need to restart your machine now

## RUN GAMESS for new molecules

Clone or [download](https://github.com/lvpp/sigma/archive/master.zip) this repository to your machine.

Uncompress the contents of the **sigma-master.zip** file.

Either construct a **.mol** file for the molecule you need or simply choose one already available in the **mol** folder.

Copy all the **.mol** files you want to process to the **GAMESS** folder.
In a terminal, access the **GAMESS** folder and run **scons** as follows:
```
cd sigma-master/GAMESS
scons
```

By doing this, all the **.mol** files in the folder will be processed and the results will be in **.gout** files.
GAMESS can take several minutes to process a molecule. The larger the molecule the longer it takes.
