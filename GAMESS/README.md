# GAMESS INSTRUCTIONS

## INSTALATION GUIDE

First of all, you need to [download](http://www.msg.ameslab.gov/gamess/download.html) **GAMESS** package.

**Run the configuration script:**
```
./config
```

**Enter the ddi folder and compile ddi:**
```
./compddi
```

**Copy ddikick.x to ../**

**To compile all modules:**
```
./compall
```

**To link the modules:**
```
./lked
```

**Add the following to /etc/sysctl.conf**
```
# gamess shmem
kernel.shmmax=560525368
```

## RUN GAMESS

The .mol, Sconstruct and keys.gamess files must be in a same folder. In a terminal, access this folder and run this following command:
```
scons
```
