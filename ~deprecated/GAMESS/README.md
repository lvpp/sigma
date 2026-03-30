# GAMESS INSTRUCTIONS

This is deprecated and will be removed in the future.
Please check the [cosmo folder](https://github.com/lvpp/sigma/tree/master/cosmo) for instructions
on how to process your own molecules.

## INSTALATION GUIDE

Currently we have instructions for Ubuntu Linux machines only and GAMESS Apr202017R1.

First of all, you need to [download](http://www.msg.ameslab.gov/gamess/download.html) the **GAMESS** package.

You need to create a folder called **src** within your **home folder**. Unzip the downloaded **GAMESS** package inside
that folder. Make sure the final directory is called **/home/lvpp/src/gamess**, where **lvpp** should be your own username.

**Install the required packages**
```
sudo apt-get install scons openbabel csh gfortran wget patch python-psutil
```

**Using a terminal, enter the GAMESS package folder and run the configuration script:**
```
cd $HOME/src/gamess
./config
```
This script will ask you several questions, typical answers for a Ubuntu Linux Desktop are:
 * Machine type: **linux64**
 * Directories: **$HOME/src/gamess**
 * Version: **0.0**
 * FORTRAN compiler: **gfortran**
 * GFORTRAN version: **4.8**, apparently even for more recent versions you need to put 4.8
 * Standard math library: **none**, or another optimized library if you know what you're doing
 * Comunication library: **sockets**, as we will not rely on parallel computing 
 * LIBCCHEM: **no** 

**Enter the ddi folder, compile ddi, and copy to ../:**
```
cd $HOME/src/gamess/ddi
./compddi
cp ddikick.x ../
```

**Patch the COSPRT routine:**

```
cd $HOME/src/gamess/source/
wget https://raw.githubusercontent.com/lvpp/sigma/master/GAMESS/cosprt.patch
mv cosprt.src cosprt.src.original
patch cosprt.src.original -i cosprt.patch -o cosprt.src
```

**Fix the paths in RUNGMS, edit the file $HOME/src/gamess/rungms and make sure it reads:**

```
set TARGET=sockets
set SCR=$HOME/src/gamess/scr
set USERSCR=$HOME/src/gamess/scr
set GMSPATH=$HOME/src/gamess
```

**Create the working folder SCR:**
```
cd $HOME/src/gamess
mkdir scr
```

**Go back to the top folder and compile all modules:**
```
cd $HOME/src/gamess
./compall
```

**Finally, link the modules:**
```
./lked
```

**Increase the shared memory maximum, add the following lines to the end of your /etc/sysctl.conf**
```
sudo gedit /etc/sysctl.conf
```
Add the following lines after the last line and save the file
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

### Special cases

Linear molecules should use the **.moll** extension as opposed to **.mol**.
This will disable some of the options that speedup the calculation for other molecues but fail if the molecule is linear.

Cations and Anions also require special options. For these cases the files should end with either **+1.mol** or **-1.mol**.

### Calculating all molecules

In order to calculate all molecules of the database (a very slow process) the following command can be used:
```
scons dist=true -j 8
```

The `-j 8` argument run 8 molecules in parallel, adjust this to the number of cores available on the machine.