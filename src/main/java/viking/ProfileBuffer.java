package viking;

public class ProfileBuffer {

    private Double[][] leftProfile;
    private Double[][] rightProfile;

    public ProfileBuffer(String folder) {
        leftProfile = CSVFileManager.pathLeft(folder);
        rightProfile = CSVFileManager.pathRight(folder);
    }

    public Double[][] getLeftProfile() {
        return leftProfile;
    }

    public Double[][] getRightProfile() {
        return rightProfile;
    }
}
