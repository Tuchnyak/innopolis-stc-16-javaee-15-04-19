package ru.innopolis.stc16.tasks.hw19.entity;

public abstract class PersonBuilderTemplate {

    public final Person run(String... args) {
        setName(args[0]);
        setBirthDate(args[1]);
        setEmail(args[2]);
        setPhone(args[3]);

        return build();
    }

    public abstract void setName(String arg);

    public abstract void setBirthDate(String arg);

    public abstract void setEmail(String arg);

    public abstract void setPhone(String arg);

    public abstract Person build();

}
