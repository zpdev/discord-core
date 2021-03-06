package com.zp4rker.discore.extensions

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Role
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction

/**
 * @author zp4rker
 */

fun Member.addRole(role: Role): AuditableRestAction<Void> {
    return guild.addRoleToMember(this, role)
}

fun Member.removeRole(role: Role): AuditableRestAction<Void> {
    return guild.removeRoleFromMember(this, role)
}