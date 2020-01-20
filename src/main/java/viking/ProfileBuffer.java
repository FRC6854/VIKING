package viking;

public class ProfileBuffer {

    private Double[][] leftProfile;
    private Double[][] rightProfile;

    public ProfileBuffer(String folder) {
        leftProfile = CSVFileManager.pathLeft("/home/lvuser/paths/" + folder);
        rightProfile = CSVFileManager.pathRight("/home/lvuser/paths/" + folder);
    }

    public Double[][] getLeftProfile() {
        return leftProfile;
    }

    public Double[][] getRightProfile() {
        return rightProfile;
    }
}
