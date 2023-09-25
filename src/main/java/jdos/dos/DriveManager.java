package jdos.dos;

import java.util.Vector;

import jdos.misc.Log;
import jdos.misc.setup.Section;

public class DriveManager {
    static int currentDrive;
    private static final DriveInfo[] driveInfos = new DriveInfo[Dos_files.DOS_DRIVES];

    public static void AppendDisk(int drive, Dos_Drive disk) {
        driveInfos[drive].disks.add(disk);
    }

    public static void InitializeDrive(int drive) {
        currentDrive = drive;
        DriveInfo driveInfo = driveInfos[currentDrive];
        if (driveInfo.disks.size() > 0) {
            driveInfo.currentDisk = 0;
            Dos_Drive disk = (Dos_Drive) driveInfo.disks.elementAt(driveInfo.currentDisk);
            Dos_files.Drives[currentDrive] = disk;
            disk.Activate();
        }
    }

    public static int UnmountDrive(int drive) {
        int result = 0;
        // unmanaged drive
        if (driveInfos[drive].disks.size() == 0) {
            result = Dos_files.Drives[drive].UnMount();
        } else {
            // managed drive
            int currentDisk = driveInfos[drive].currentDisk;
            result = ((Dos_Drive) driveInfos[drive].disks.elementAt(currentDisk)).UnMount();
            // only delete on success, current disk set to NULL because of UnMount
            if (result == 0) {
                driveInfos[drive].disks.clear();
            }
        }
        return result;
    }

//	MAPPER_AddHandler(&CycleDisk, MK_f3, MMOD1, "cycledisk", "Cycle Disk");
//	MAPPER_AddHandler(&CycleDrive, MK_f3, MMOD2, "cycledrive", "Cycle Drv");

    //	static void CycleDrive(bool pressed);
//	static void CycleDisk(bool pressed);
    public static void CycleAllDisks() {
        for (int idrive = 0; idrive < Dos_files.DOS_DRIVES; idrive++) {
            int numDisks = driveInfos[idrive].disks.size();
            if (numDisks > 1) {
                // cycle disk
                int currentDisk = driveInfos[idrive].currentDisk;
                Dos_Drive oldDisk = (Dos_Drive) driveInfos[idrive].disks.elementAt(currentDisk);
                currentDisk = (currentDisk + 1) % numDisks;
                Dos_Drive newDisk = (Dos_Drive) driveInfos[idrive].disks.elementAt(currentDisk);
                driveInfos[idrive].currentDisk = currentDisk;

                // copy working directory, acquire system resources and finally switch to next drive
                newDisk.curdir = oldDisk.curdir;
                newDisk.Activate();
                Dos_files.Drives[idrive] = newDisk;
                Log.log_msg(
                    "Drive " + ('A' + idrive) + ": disk " + (currentDisk + 1) + " of " + numDisks + " now active");
            }
        }
    }

    public static void Init(Section sec) {
        // setup driveInfos structure
        currentDrive = 0;
        for (int i = 0; i < Dos_files.DOS_DRIVES; i++) {
            driveInfos[i] = new DriveInfo();
            driveInfos[i].currentDisk = 0;
        }
    }

    private static class DriveInfo {
        Vector disks = new Vector();
        /*Bit32u*/ int currentDisk;
    }
}
