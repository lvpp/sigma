## GAMESS INSTALATION INSTRUCTIONS

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

 # gamess shmem
 kernel.shmmax=560525368