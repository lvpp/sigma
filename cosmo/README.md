# COSMO INSTRUCTIONS

## NWCHEM INSTALATION GUIDE

For the sigma-LVPP 2025 release and forward we use [NWChem](https://nwchemgit.github.io/) to generate COSMO files.

Here we provide installation instructions for Ubuntu 24.04.

### Install from standard package manager

```
sudo apt install nwchem
```

Future releases of NWChem should include the features used to build sigma-LVPP COSMO profiles.
For now you will probably need to compile from souces, as described below. 

### Compile the modified NWChem sources

**Install the required packages**
```
sudo apt-get install gfortran libopenmpi-dev
```

**Using a terminal, clone the LVPP modified nwchem source:**
```
git clone --single-branch --branch ses https://github.com/lvpp/nwchem.git
```

This will will clone the LVPP modified nwchem with the SES (solvent-excluding surface) feature.

**Enter the nwchem folder and configure it:**
```
cd nwchem/src/
```

**Build the application:**
```

```

## Process your own molecules

Please check either the [cosmo folder](https://github.com/lvpp/sigma/tree/master/cosmo) for instructions
on how to process your own molecules.
