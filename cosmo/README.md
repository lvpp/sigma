# COSMO Instructions

## NWChem Installation Guide

Starting with the sigma-LVPP 2025 release, we use [NWChem](https://nwchemgit.github.io/) to generate COSMO files and sigma-profiles.

### For Ubuntu Linux (64-bit)

This guide provides installation instructions for Ubuntu Linux 24.04.

#### Option 1: Install from Package Manager

```bash
sudo apt install nwchem scons
````

Future versions of NWChem may include the necessary features to build sigma-LVPP COSMO files.
For now, you will likely need to compile it from source, as described below.

#### Option 2: Compile Modified NWChem Sources

**Step 1: Install Required Packages**

```bash
sudo apt install gfortran libopenmpi-dev scons
```

**Step 2: Clone the LVPP-Modified NWChem Repository**

```bash
git clone --single-branch --branch ses https://github.com/lvpp/nwchem.git
```

This command clones the LVPP-modified version of NWChem, which includes the SES (solvent-excluding surface) feature.

**Step 3: Set the NWChem top folder**

```bash
export NWCHEM_TOP=~/nwchem
```
Make sure to adjust the `NWCHEM_TOP` variable above to match your system.


**Step 4: Configure the Build**

```bash
cd nwchem/src/
make nwchem_config \
  NWCHEM_TARGET=LINUX64 \
  USE_MPI=y \
  BUILD_OPENBLAS=1 \
  BUILD_SCALAPACK=1 \
  BLAS_SIZE=8 \
  SCALAPACK_SIZE=8 \
  NWCHEM_MODULES=qm
```

**Step 5: Build NWChem**

```bash
make NWCHEM_TARGET=LINUX64 \
  USE_MPI=y \
  BUILD_OPENBLAS=1 \
  BUILD_SCALAPACK=1 \
  BLAS_SIZE=8 \
  SCALAPACK_SIZE=8 \
  NWCHEM_MODULES=qm
```

The compilation process can take several minutes. The resulting executable will be available at `nwchem/bin/LINUX64/nwchem`.

---

## Process Your Own Molecules

First, clone the sigma-LVPP repository:

```bash
git clone https://github.com/lvpp/sigma.git
```

Or [download](https://github.com/lvpp/sigma/archive/master.zip) and extract the contents manually.

### Enter the COSMO folder:

```bash
cd sigma/cosmo/nw-b3lyp-svpd-18/
```

Edit the `SConstruct` file and adjust the path to your NWChem build:

```python
nwchem = f"{os.environ['HOME']}/nwchem/bin/LINUX64/nwchem"
basis = f"{os.environ['HOME']}/nwchem/src/basis/libraries/"
```

### Add Your Molecules

Copy or save `.xyz` files for all molecules you want to use in the COSMO calculation into this folder.

### Run SCons

```bash
scons -j 6
```

This executes up to 6 parallel jobs. Each `.xyz` file generates one `.cosmo` output.

Molecular geometry is not optimized by this task.
Please provide optimized structures in your `.xyz` files.
Instructions on how to optimize the geometry are given in the [geometry folder](../geometry/).


### For Windows

This guide explains how to set up NWChem and run COSMO calculations on Windows.

#### Step-by-Step Guide

1. **Download NWChem**
   Download the NWChem ZIP archive from `https://chasquebox.ufrgs.br/public/ece49d`

2. **Extract the ZIP File**
   Extract the contents to a convenient location, such as `C:\Users\<user_name>\Downloads\nwchem\`.

3. **Install MS-MPI**
   Run `MSMpiSetup.exe` inside the extracted folder to install Microsoft MPI.

4. **Clone the Sigma Repository**
   Open a terminal and run:

   ```bash
   git clone -b gas-cosmo --single-branch https://github.com/lvpp/sigma.git
   ```

5. **Configure COSMO Method**

   * Navigate to `sigma/cosmo/<selected_basis>`, e.g., `cosmo/b3lyp-d2svpd/`.
   * Edit `SConstruct`:

     ```python
     nwchem = 'C:/Users/<user_name>/Downloads/nwchem/nwchem.exe'
     basis = 'C:/Users/<user_name>/Downloads/nwchem/basis/'
     ```

6. **Install Required Python Packages**
   Open a terminal and run:

   ```bash
   pip install SCons rdkit
   ```

7.  **Transfer `.xyz` Files**
   
    Copy or save `.xyz` files for all molecules you want to use in the COSMO calculation into this folder.
    
8.  **Run COSMO Calculations**
    Navigate to the COSMO folder:

    ```bash
    cd C:\Users\<user_name>\sigma\cosmo\<selected_basis>
    scons
    ```

9.  **Optional: Parallel Execution**
    To run multiple jobs in parallel (e.g., 6):

    ```bash
    scons -j 6
    ```
Molecular geometry is not optimized by this task. Please provide optimized structures in your `.xyz` files. Instructions on how to optimize the geometry are given in the [geometry folder](../geometry/).

---
## Check NWChem Directives

By default, B3LYP and def2-SVPD are used.
If you want to customize the NWChem directives used to generate the COSMO files, check the `base-keys.txt` file. 