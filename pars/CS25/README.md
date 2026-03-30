# CS25 parametrization

The **LVPP-modified COSMO-SAC 2025**, referred to simply as **CS25**, all global parameters considered are available in the [globals.csv file](./globals.csv) while the dispersion binary parameters are available in the [binary.csv file](./binary.csv).

### Sigma-Profiles

CS25 is based on sigma-profiles calculated using [NWChem](https://nwchemgit.github.io/) as the quantum chemical package, in contrast to previous parametrizations that relied on GAMESS.
The level of theory employed is **B3LYP/def2-SVPD**.
Molecular geometries were first optimized in the gas phase, followed by a single-point calculation with the **COSMO** solvation model to generate the sigma-profiles.

The **def2-SVPD** basis set was chosen as a balance between accuracy and computational cost, offering improved polarization treatment and broader elemental coverage compared to the TZVP basis set used previously.
For larger molecules, the **def2-SVP** basis set was adopted to reduce computational demand, with negligible loss of accuracy in the resulting sigma-profiles.

### Multiple Hydrogen Bond Types

In this parametrization, different types of *strong* hydrogen bonds (HBs) are explicitly considered.

Two types of strong HB donors are defined: one corresponding to water molecules and another representing hydrogens bonded to electronegative atoms such as N, O, F, Cl, Br, or I.

For strong HB acceptors, several categories are available: *ketone*, *ether*, *amine*, and *F+*.
Some special cases apply to nitrogen-containing groups:

- When nitrogen is bonded to a single atom (e.g., in nitriles), it is treated as a *ketone*-type acceptor.  
- When bonded to two atoms (e.g., in pyridine), it is treated as an *ether*-type acceptor.

Besides being part of one of the categories listed before. Only surface area fractions with a charge density above the HB cutoff are treated as capable of forming *strong* hydrogen bonds. The standard electrostatic contribution already accounts for the general hydrogen-bonding interaction, but segments exceeding the cutoff receive an **additional, strong HB contribution**. This extra term represents situations with particularly strong hydrogen bonds, typically associated with shorter interaction distances.

### Dispersion Contribution

The original COSMO-SAC model typically disregarded dispersion interactions, assuming that their effects would cancel out in excess properties.
In **CS25**, a dedicated dispersion contribution is included for each pair of atoms, check the [binary.csv file](./binary.csv).
This is achieved by storing, along with the surface charge density (sigma-profile), the **atom type** associated with each surface segment.

Although the resulting dispersion term is generally small, it provides a subtle yet meaningful correction that improves the description of nearly athermal mixtures, systems dominated by weak interactions, and particularly **fluorinated/hydrocarbon mixtures**, where dispersion effects play a more significant role.

### Combinatorial contribution

A modified Flory-Huggins (FH) equation is used for the combinatorial contribution where volume fractions are raised to an empirical exponent = 2/3.

This is in contrast to the typical Staverman-Guggenheim (SG) term with a normalized area.
The reason for using FH is because the potential inconsistencies with the SG formula.
